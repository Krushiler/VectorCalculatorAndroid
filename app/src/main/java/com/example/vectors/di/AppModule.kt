package com.example.vectors.di

import com.example.vectors.domain.vector.RealVectorCalculator
import com.example.vectors.domain.vector.VectorCalculator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideVectorCalculator(): VectorCalculator = RealVectorCalculator()
}