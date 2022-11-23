package com.example.vectors.di

import com.example.vectorcalculations_android.vectorcalculations.matrix.MatrixCalculator
import com.example.vectorcalculations_android.vectorcalculations.matrix.RealMatrixCalculator
import com.example.vectorcalculations_android.vectorcalculations.vector.RealVectorCalculator
import com.example.vectorcalculations_android.vectorcalculations.vector.VectorCalculator
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
    fun provideVectorCalculator(): VectorCalculator =
        RealVectorCalculator()

    @Provides
    @Singleton
    fun provideMatrixCalculator(vectorCalculator: VectorCalculator): MatrixCalculator =
        RealMatrixCalculator(vectorCalculator)

}