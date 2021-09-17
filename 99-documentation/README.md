# **Universal Beats**

# Table of contents
1. <a href="#about">About</a>
2. <a href="#short-movie">Short movie</a>
3. <a href="#progressive-web-app">Progressive Web App</a>
4. <a href="#native-mobile-app">Native Mobile App</a>
5. <a href="#nodejs-app">NodeJS App</a>
6. <a href="#laravel-app">Laravel App</a>

# About
Universal Beats is a platform where you can buy beats and sell beats. If you want to log into an existing account, all passwords are the same: "useruser".
We have 2 mobile apps for Universal Beats, a Progressive Web App and a Native Mobile App. We specify the extra functionalities at their respective column.

# Short movie
[![Universal Beats](https://img.youtube.com/vi/xWhv-OC2FYU/0.jpg)](https://www.youtube.com/watch?v=xWhv-OC2FYU "Universal Beats")

# Progressive Web App
### About
For the PWA you have 1 extra functionality. You can view the insights of your beat, so how many times your beat has been played and on which days.

You can check out the PWA on https://universalbeats.web.app/. View it as a phone to have the best experience.

### Installation
You can install the PWA by cloning <a target="_blank" href="https://git.ti.howest.be/TI/2020-2021/s4/web-and-mobile-technology/students/marvin-deckmyn/project/01-pwa">01-pwa</a>.

If you have a Laravel App server that runs locally, you need to change 2 things in script.js:
```
const BASE = "http://universal-beats.local";
const BASE_API = "http://universal-beats.local/api";
```
This is purely an example of a local Laravel server, so it can be named differently at your local PC.

### All the must haves are implemented
- There is a correct use of SASS.
- An app shell has been implemented.
- There is use of a web manifest file.
- You can install this PWA easily on mobile devices. For example on Android you get a special pop-up to install this PWA.
- Service workers have been used.
- IndexedDB has been used for the cart system.
- Cache API has been used for when the user has no internet connection.
- You get a push notification when you have sold a beat.
- The PWA is hosted through HTTPS on Firebase.

# Native Mobile App
### About
For the NMA you have 2 extra functionalities. The first one is that you can upload beats in the NMA. You can select the beat that's stored on your phone and you can upload it immediately. You can also add a cover that you can select from your image gallery.

The second extra functionality is that you can download beats in the NMA and this will be stored directly on your phone.

### Installation
You can install the NMA on your Android mobile phone via <a href ="https://git.ti.howest.be/TI/2020-2021/s4/web-and-mobile-technology/students/marvin-deckmyn/project/99-documentation/-/raw/master/universal-beats.apk">the APK file</a>. 

If you want to run it locally on your PC, you have to install <a href="https://developer.android.com/studio/install">Android Studio</a> and you can clone the <a target="_blank" href="https://git.ti.howest.be/TI/2020-2021/s4/web-and-mobile-technology/students/marvin-deckmyn/project/02-nma">02-nma</a> project and open the project there.
If you want to change the server link, you have to change 2 things.

First, you go to 'UniversalBeatsApiService.kt' and change the BASE_API_URL.
```
private const val BASE_API_URL = "https://fierce-mesa-02091.herokuapp.com/api/"
```

Secondly, you go to 'strings.xml' and change "base_url".
```
<string name="base_url">https://fierce-mesa-02091.herokuapp.com/</string>
```

##### Pure local
If you run the NodeJS server locally, you have to change these base urls to the IP of your PC. You run 'ipconfig /all' and get the IPv4 address that starts with '192.168.'.

Then you go to the settings of your Android emulator. You go to 'Proxy', choose 'Manual proxy configuration' and the 'Host name' should be the IPv4 address. The port number should be '8888'. Then you click on 'Apply' and it should be successful.

In Android Studio you have to change the base urls into that IP and you can't use HTTPS, so you have to use HTTP.

### All the must haves are implemented and there's also a nice to have
- There are at least 4 Activities that displays a layout resource
- There is a menu-based navigation. You can navigate to Home, to Beats, to Start Selling and lastly to Profile. There is also navigation on the Profile fragment. On the right-top you can navigate again to Home, to Sold Beats, to Settings and lastly to log out.
- A Room database is used to store user data when the user is logged in.
- Retrofit is used for communication with the API service. The API services is brought by the NodeJS server.
- A notification will be sent once a week from the app to boost user engagement. The WorkManager is used here to do this periodically.
- There are at least 2 Implicit Intents. Examples: an intent for picking audio from your files and intent for picking an image from your gallery.
- There will be a notification when downloading a beat.
- There is a register & login system (nice to have).

# NodeJS App
### About
The NodeJS App is used as a server to store information in a MySQL database and also get information from there. It's also used to connect to it via API routes.

The applications is sufficiently large because it has multiple routes and the data logic & server logic are separated.

There are no views because it is not needed as a server. This is purely a back-end application so no views are needed. If you want to use views, you have to specify it with
```
app.set('views', path.join(...));
```
with a path to your ejs file. 

Then you have to specify
```
app.set('view engine', 'ejs')
```
and you have configured the views!

There is a MySQL database connection and there's also a 'fs' to write beats or cover images in the NodeJS app. These files are stored in the 'www' folder.
### Installation
You can install the NodeJS app by cloning <a target="_blank" href="https://git.ti.howest.be/TI/2020-2021/s4/web-and-mobile-technology/students/marvin-deckmyn/project/03-nodejs">03-nodejs</a>. You also need the MySQL database and you can download <a href="https://git.ti.howest.be/TI/2020-2021/s4/web-and-mobile-technology/students/marvin-deckmyn/project/99-documentation/-/raw/master/universal-beats-nodejs-mysql.sql?inline=false">the .sql file here</a>.

Once cloned, you have to install the npm modules by running 'npm install'.
Then you have to run the .sql query that you installed.

If you want to run this locally, you need to change the config in data.js:
```
let config = {
    host:       "localhost",
    port:       3306,
    database:   "universal-beats",
    user:       "universal-user",
    password:   "universal-password",
};
```

Then you also need to change this in main.js:
```
server.listen(8888);
```

Then you run 'node main.js' and you can go to http://localhost:8888/api/beats and you can see the beats!

# Laravel App
### About
The Laravel App is used for storage and for storing information in a database. It's also used to connect to it via API routes.

### Installation
For the Laravel app, I used the Vagrant box 'Laravel Homestead' so I recommend you to use that as well and install the basic things like PHP.

You can install the Laravel app by cloning <a target="_blank" href="https://git.ti.howest.be/TI/2020-2021/s4/web-and-mobile-technology/students/marvin-deckmyn/project/04-laravel">04-laravel</a>.

Once cloned, you should add a site and a database to your homestead.yaml file. You should also update your hostfile and then normally you can visit the site. I called it 'universal-beats' and that's also how the database is called, so if you change this, you need to change the .env file so you can connect with the database

Then you have to update the composer
```
composer update
```

The app is useless without a database so you need to run the migrations and seeders:
```
php artisan migrate
php artisan db:seed
```

Then, you need to create symbolic links, so you can upload on the site:
```
php artisan storage:link
```

Now you're ready to go!

### All the must haves are implemented and there's also a nice to have
- There is laravel data validation.
- There are CRU routes.
- There is minimum 1 route with a parameter.
- There is minimum 1 eloquent model with a relationship.
- There are migrations and seeders.
- The moods table is multilingual.
- The necessary layers are used.
- There is API authentication via JWT (nice to have).
