package com.safeboda.test.di.modules

import android.content.Context
import com.safeboda.test.SafeBodaTestApp
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [AppComponent].
 *
 * @see Module
 */
@Module
class AppModule {

    /**
     * Create a provider method binding for [Context].
     *
     * @param application Sample Application.
     * @return Instance of context.
     */
    @Provides
    fun provideContext(application: SafeBodaTestApp): Context {
        return application.applicationContext
    }

}
