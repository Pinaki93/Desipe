/*
 * MIT License
 *
 * Copyright (c) 2020 Pinaki Acharya
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.pinaki.desipe.di.module

import android.os.Build
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pinaki.desipe.BuildConfig
import dev.pinaki.desipe.di.InjectionConstants
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {

    @Provides
    @Named(InjectionConstants.KEY_DATABASE_NAME)
    fun providesDatabaseName() = BuildConfig.DATABASE_NAME

    @Provides
    @Named(InjectionConstants.KEY_IS_DEBUG_BUILD)
    fun providesIsDebugBuild() = BuildConfig.DEBUG

    @Provides
    @Named(InjectionConstants.KEY_BASE_URL)
    fun providesBaseUrl() = BuildConfig.SERVER_URL

    @Provides
    @Named(InjectionConstants.KEY_PREFERENCES_NAME)
    fun providesSharedPrefName() = BuildConfig.SHARED_PREFS_NAME

    @Provides
    @Named(InjectionConstants.KEY_OS_VERSION)
    fun providesOSVersion() = Build.VERSION.SDK_INT
}