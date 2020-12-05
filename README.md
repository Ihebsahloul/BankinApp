# Bankin App Demo [mvvmclean]

An Android app consuming [Banking App API](https://raw.githubusercontent.com/bankin-engineering/challenge-android/master/categories.json) to display the expenses categories and sub categories
of theb Bankin' Application. It has been built using kotlin with clean architecture principles, Repository Pattern and MVVM
pattern as well as Architecture Components.

Min API Level Supported : 19


## Development Environment

    Android Studio 4.1.1
    macOS BigSur 11.0

## Table of Contents

- [Architecture Blueprint](#architecture)
- [Features](#features)
- [Libraries](#libraries)
- [Extras](#extras)
- [Screenshots](#screenshots)

## Architecture

The Application is split into a three layer architecture inorder to provide clean separation of concerns - making the code easier to navigate and maintain.
- Data - Layer that holds APIs, Database, Cache
- Domain - Layer that holds Use Cases, and Model Objects. Business logic happens here.
- Application - Layer that holds presentation, Android components, Viewmodels, Dagger components/modules handles Dependency Injection, etc. MVVM exists at this layer.

![Data Flow Diagram](media/arch-flow.png)

The three layered architectural approach is majorly guided by clean architecture which provides
a clear separation of concerns.

## Features
 
 - Trending Repository Listing
 - Repository sorting by Value and name
 - Pull to refresh category
 - Offline Storage ( Scheduled repo remote controller syncing, App will sync with backend server in every 2hrs )
 
 ## Testing

 - `app/test/ - Unit tests` - test -> Right click on package name(com.bankin.task) -> Run Test In 'com.bankin.task'
 - `app/androidTest/ - Instrumentation tests` - androidTest -> Right click on package name(com.bankin.task) -> Run Test In 'com.bankin.task'

## Libraries

Following are the Libraries used:

- [Material Design](https://material.io/develop/android/docs/getting-started/) - Google material design UIs.
- [Dagger2](https://github.com/google/dagger) - Dependency Injection lib with large community support.
- [Retrofit](https://square.github.io/retrofit/) - Network Http Client
- [Jetpack](https://developer.android.com/jetpack)
  - [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Channel between use cases and UI
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - For binding of UI components in layouts to data sources, and coroutines support.
- [Moshi](https://github.com/square/moshi) - Data, Model & Entity JSON Parser that understands Kotlin non-nullable and default parameters
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - logs HTTP request and response data.
- [Mockito](https://site.mockito.org/) - Mocking framework used in unit tests.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines, provides `runBlocking` coroutine builder used in tests
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) - web server for testing HTTP clients.
- [Leak Canary](https://square.github.io/leakcanary/) - Leak Detection Library
- [Espresso](https://developer.android.com/training/testing/espresso) - Test framework to write UI Tests
- [recyclerview-animators](https://github.com/wasabeef/recyclerview-animators) - Recycler View Animations
- [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room) - Robust database access while harnessing the full power of SQLite
- [Robolectric](http://robolectric.org/) - Android Unit Tests framework.
- [Truth](https://truth.dev/) - Provides fluent assertions for Java and Android

## Extras


#### Resource Values

- Fonts
- Dimension & String Values
- Themes & Styles
- Network Config

## Screenshots

- Architecure

<p float="center">
  <img src="media/arch-presentation.png" title="Presentation Layer" width="285" />
  <img src="media/arch-domain.png" title="Domain Layer" width="285" align="top" />
  <img src="media/arch-data.png" title="Data Layer" width="285" align="top"/> 
</p>

##

- App Screens

<p float="center">
  <img src="media/app-screen-loading.png" title="Repository Loading" width="285" />
  <img src="media/app-screen-list.png" title="Repositories List" width="285" /> 
  <img src="media/app-screen-expanded.png" title="Repositories List Expanded" width="285" />
  <img src="media/app-screen-sort.png" title="Repo Sort" width="285" />
  <img src="media/app-screen-reload.png" title="Repository Refresh" width="285" />
  <img src="media/app-screen-retry.png" title="Error Retry" width="285" />
</p>


## License

 ```
   Copyright 2020 Iheb SAHLOUL

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 ```

