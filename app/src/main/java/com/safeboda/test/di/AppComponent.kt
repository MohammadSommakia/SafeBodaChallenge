package com.safeboda.test.di

import com.safeboda.data.di.ApiModule
import com.safeboda.data.di.PersistenceModule
import com.safeboda.data.di.RepositoryModule
import com.safeboda.domain.scopes.AppScope
import com.safeboda.test.SafeBodaTestApp
import com.safeboda.test.di.modules.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule

/**
 * App component that application component's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(

    modules = [
        AppModule::class,
        ActivityModule::class,
        AndroidInjectionModule::class,
        ApiModule::class,
        RepositoryModule::class,
        PersistenceModule::class,
    ]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: SafeBodaTestApp)


}
