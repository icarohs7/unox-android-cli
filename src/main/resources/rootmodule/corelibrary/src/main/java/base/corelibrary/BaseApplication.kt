package base.corelibrary

abstract class BaseApplication : Application() {
    @CallSuper
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        setupKoin()
        setupTimber()
        setupUnoxAndroid()
        setupUnoxAndroidArch()
        setupKotpref()
        lockOrientation()
        setupMaterialDrawerImageLoading()
    }

    private fun setupKoin() {
        startKoin(this, onCreateKoinModules())
    }

    open fun onCreateKoinModules(): List<Module> {
        return listOf(module {})
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupUnoxAndroid(): Unit = UnoxAndroid.run {
    }

    private fun setupUnoxAndroidArch(): Unit = UnoxAndroidArch.run {
        init()
        val transition = UnoxAndroidArch.AnimationType.FADE
        setActivityAndFragmentTransitionAnimation(transition)
    }

    private fun setupKotpref() {
        Kotpref.init(applicationContext)
        Kotpref.gson = Gson()
    }

    /**
     * Override to use another orientation
     * throughout the apllication.
     * List of option on [ActivityInfo].
     * Return null to use user defined
     * orientation
     */
    open fun onSelectOrientation(): Int? {
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun lockOrientation() {
        val orientation = onSelectOrientation() ?: return

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                activity.requestedOrientation = orientation
            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }
        })
    }

    private fun setupMaterialDrawerImageLoading() {
        DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
            private val picasso by lazy { Picasso.get() }

            override fun placeholder(ctx: Context, tag: String?): Drawable {
                return DrawerUIUtils.getPlaceHolder(ctx)
            }

            override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable?, tag: String?) {
                picasso.load(uri)
                        .also { placeholder?.let(it::placeholder) }
                        .into(imageView)
            }

            override fun cancel(imageView: ImageView) {
                picasso.cancelRequest(imageView)
            }
        })
    }
}
