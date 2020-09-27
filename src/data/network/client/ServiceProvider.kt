package picfinder.data.network.client

import picfinder.data.network.services.ImgurPictureService
import picfinder.data.network.services.UnsplashPictureService

val RestClient.unsplashService: UnsplashPictureService
    get() = unsplashRetrofit.create(UnsplashPictureService::class.java)

val RestClient.imgurService: ImgurPictureService
    get() = imgurRetrofit.create(ImgurPictureService::class.java)