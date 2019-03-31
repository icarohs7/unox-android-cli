package base.corelibrary.domain.services

import arrow.core.Try
import base.corelibrary.domain.broadcastreceivers.LambdaBroadcastReceiver

abstract class BaseAlwaysRunningService : Service() {
    private val worker: Thread = Thread(::doWork)

    /**
     * Define the behavior of the worker thread
     * of the service, do not forget to implement
     * a verification and cancelation when the thread
     * is interrupted
     */
    abstract fun doWork()

    /**
     * Define how the service will be created
     */
    abstract fun onServiceCreate(context: Context)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Try { worker.start() }
        return Service.START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Try { worker.interrupt() }
        LambdaBroadcastReceiver.broadcast { _, context -> onServiceCreate(context ?: appCtx) }
        super.onDestroy()
    }
}