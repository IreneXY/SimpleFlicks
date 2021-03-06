# Project 1 - *SimpleFlicks*

**SimpleFlicks** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: 14 hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current movies** from the Movie Database API
* [x] Layout is optimized with the [ViewHolder](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#improving-performance-with-the-viewholder-pattern) pattern.
* [x] For each movie displayed, user can see the following details:
  * [x] Title, Poster Image, Overview (Portrait mode)
  * [x] Title, Backdrop Image, Overview (Landscape mode)

The following **optional** features are implemented:

* [x] Display a nice default [placeholder graphic](http://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#configuring-picasso) for each image during loading.

The following **bonus** features are implemented:

* [x] Allow user to view details of the movie including ratings and popularity within a separate activity or dialog fragment.
* [x] When viewing a popular movie (i.e. a movie voted for more than 5 stars) the video should show the full backdrop image as the layout.  Uses [Heterogenous ListViews](http://guides.codepath.com/android/Implementing-a-Heterogenous-ListView) or [Heterogenous RecyclerView](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) to show different layouts.
* [x] Allow video trailers to be played in full-screen using the YouTubePlayerView.
    * [x] Overlay a play icon for videos that can be played.
    * [x] More popular movies should start a separate activity that plays the video immediately.
    * [x] Less popular videos rely on the detail page should show ratings and a YouTube preview.
* [ ] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.
* [ ] Apply rounded corners for the poster or background images using [Picasso transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#other-transformations)
* [ ] Replaced android-async-http network client with the popular [OkHttp](http://guides.codepath.com/android/Using-OkHttp) networking libraries.

The following **additional** features are implemented:

* [x] Splash screen

## Video Walkthrough

Here's a walkthrough of implemented user stories:

### Full

<img src='https://github.com/IreneXY/SimpleFlicks/blob/master/android/screenshots/video/full.gif' title='Full Video Walkthrough' width='' alt='Full Video Walkthrough' />

### Portrait

<img src='https://github.com/IreneXY/SimpleFlicks/blob/master/android/screenshots/video/portrait.gif' title='Portrait Video Walkthrough' width='' alt='Portrait Video Walkthrough' />

### Landscape

<img src='https://github.com/IreneXY/SimpleFlicks/blob/master/android/screenshots/video/landscape.gif' title='Landscape Video Walkthrough' width='' alt='Landscape Video Walkthrough' />

### Youtube

click to watch:
<a href="http://www.youtube.com/watch?feature=player_embedded&v=xL7fi8Fq9g0" target="_blank"><img src="http://img.youtube.com/vi/xL7fi8Fq9g0/0.jpg" width="240" height="180" border="10" /></a>

video link: https://photos.app.goo.gl/tCq6gs0sBMhg6XYl1

## Notes

Previously, in YoutubeActivity, I designed a `close` button overlaying the YouTubePlayerView on the right-top corner to close the activity. But I found the YouTube video can be only played 2 seconds and then be paused. I looked up the API doc and implemented the `YouTubePlayer.PlayerStateChangeListener` to see the YouTubePlayer.ErrorReason and I got the error reason `UNAUTHORIZED_OVERLAY` which meaned `Playback has been stopped due to a view overlaying the player.` So I remove the `close` button from the layout.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

Copyright 2017 Irene Yu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
