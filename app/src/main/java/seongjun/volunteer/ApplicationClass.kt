package seongjun.volunteer

import android.app.Application
import seongjun.volunteer.repository.Repository

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)
    }
}