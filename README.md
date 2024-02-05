# Movie Demo

If you want to clone/fork the project you will need to set in the gradle.properties the values for the TOKEN and API_KEY from [TheMovieDb API](https://developer.themoviedb.org/reference/intro/getting-started),
also add in the app:gradle the signingConfigs to build the apk signed\
For **Macropay**, you gave me the TOKEN and the API_KEY so I used it

## Update
* Added search bar, you need to clear it and click seach to fetch all movies again (fetch the movies on page 1), however it has a bug: if you search a movie and clear the searchbar the endless scroll stop working

## Credentials
You can use the next credentials:
- email: macropay123@gmail.com
- pass: mp1234

## For download apk signed
You can download the apk in the __Actions__ tab showed in the below image
<img title="Downlaod apk from github actions" src="/screenshot/where-download-apk-from-github-actions.png">

## UI

### Login
It has 2 buttons. The first one is for register: you have to create a new account with your email and create a new password; 
the second one is for log in and navigate to the home-movie view, but first you need write yours credentials (previously created);\
for both buttons you have to write a email and password, it could be anything but with at least 6 characters

### Home (Movies View)
Here you can see all the movies on the first page returned by TheMovieDb, as you scroll, it will load more movies

### Movie Details
When you select a movie, you will go to a details view where you will see more data about the movie selected, for example:
* backdrop image
* poster image
* title
* genres
* average rate
* overview
* release date
* duration

## Architecture
This demo has a clean architecture. View -> ViewModel -> UseCase -> Repository -> Service (Firebase/TheMovieDb)

## Workflow code
- Movies
  - A custom recycler view was made to keep separate the responsibilities of each class and a listener was added that is activated when you select an element of the recycler view, when you select the element you navigate to the details view that receives a movieId to get the details of the selected movie
- Details
  - This details view is a custom view that can be reused in any other part that is required in case of adding more functionalities to the app.\
Here the movie object is passed to the custom view and its own class is in charge of displaying what is required, bindingAdapter is used to simplify part of the logic and not having to rewrite code.

## Tools
- Retrofit
- Gson
- Coroutines
- Dagger Hilt (android library)
- Firebase (Auth, crashlytics and analytics)
- Navigation Component
- ViewModel and SharedFlow
- Glide
- Shimmer
- Github actions to build the apk on every release tag

## TODO
- sigup with social medias
- Handler internet errors
- UnitTest
- display error messages if something goes wrong

## Directory
- ${rootProject}/.github/workflows/build.yml
- data
  - model
  - network
  - repository
- di (modules)
- domain (useCase)
- ui (viewModel, adapter, viewHolder)
  - login
    - register
  - movies
    - details
    - home (movie list)
  - widgets
    - movie details view
    - custom movie list (recycler view)
    - movie item (recyclerView's cells)
- util (extras)
  - keys
  - objects to wrap the network response
  - function to use data binding
