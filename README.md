To solve this challenge I chose to use Alamofire library to get the patient data and parse the json. 

I created a network manager class where all the networking calls are made and the Json's are parsed into my Model object. 

Across the app I implemented an MVVM architecture to keep the VC's light and to make it easier on testing.

Some of the data from the API was missing or constantly changing, so I added default values to act as placeholders. Ideally we would alter the UI to conform to the missing data. 

I implemented an infinite scroll table that would get the results on the next page when the bottom of the list has been reached. 

I also implemented a pull to refresh from the top of the table that would load the initial results. 

Added UI test to test the flow of the app