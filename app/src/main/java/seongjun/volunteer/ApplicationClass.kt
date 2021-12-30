package seongjun.volunteer

import android.app.Application
import seongjun.volunteer.repository.Repository

class ApplicationClass: Application() {

    companion object {
        lateinit var repository: Repository
    }

    override fun onCreate() {
        super.onCreate()
        repository = Repository(this)
    }
}