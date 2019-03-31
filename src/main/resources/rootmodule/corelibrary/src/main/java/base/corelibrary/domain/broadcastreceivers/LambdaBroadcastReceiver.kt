package base.corelibrary.domain.broadcastreceivers

class LambdaBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        runBlocking {
            val action = actionsRelay.awaitFirst()
            action(this@LambdaBroadcastReceiver, context)
        }
    }

    companion object {
        private val actionsRelay: Relay<(LambdaBroadcastReceiver, Context?) -> Unit> = BehaviorRelay.create()
        private const val BROADCAST_ACTION: String = "LambdaBroadcastReceiver"

        fun broadcast(lambda: (LambdaBroadcastReceiver, Context?) -> Unit): Unit = with(appCtx) {
            actionsRelay.accept(lambda)
            sendBroadcast(Intent(this, LambdaBroadcastReceiver::class.java))
        }
    }
}
