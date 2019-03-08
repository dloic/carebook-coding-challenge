Challenge Accepted ! :D

Solution:
I used the MVVM architecture for my application. It is recommended by Google, and ensures the data integrity within lifecycle chagnes.
I also used the android architecture components for Pagination. (PageKeyedDataSource)
I made sure to display a progress bar while loading and a snackbar for retrying if the request fails for some reason.
I added some kotlin doc, when relavant for understanding.
I used constraint layouts, as recommended by Google, for a responsive UI.
I used a snackbar if the fetch fails in order to let the user have a chance to reload the content without killing the app and restarting it.
I wrote a small unit test to ensure that the response from the server, which i copied in a .json file is a valid JSON format
I took some time to remove the lint warnings that were present 
The strings are also localized in French :)

I used the following external frameworks:
1. Dagger 2 for dependency injection (com.dagger)
2. Retrofit  as a rest Client  (com.squareup)
3. OkHttp for handling network requests (com.squareup)
4. Gson converter to process JSON (and not have to manually deserialize) (com.squareup)

Things I thought about but seemed overkill:
1. Room for persisting the data  

Issue encountered: 
Api response changing sporadically. 
Solution -> Add null checks for the names of the patients (The id's are always available)

Thank you !
JR
