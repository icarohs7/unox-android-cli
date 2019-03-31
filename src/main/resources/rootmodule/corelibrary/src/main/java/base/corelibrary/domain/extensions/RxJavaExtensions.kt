package base.corelibrary.domain.extensions

fun <T> Flowable<T>.setupThreads(): Flowable<T> {
    return this.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
}
