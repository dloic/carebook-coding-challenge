
![carebook](https://carebook.com/wp-content/uploads/2018/08/Carebook.png)

Hi dear developer!

We believe in a world where everyone is empowered to be actively engaged and fully supported in managing and improving their overall health.

Your challenge is to build a simple app that displays a list of patients and the ability to see the details of a patient.

# Requirements

- The app has a simple onboarding screen to explain what the app is about
- The app has a list screen, displaying patients from a paginated API (so be sure pagination is working fine in your list). 
- The app has a detail screen, showing more information about the patient (min of 4 additional fields)
- Avoid Massive View Controller implementation

# Non-functional requirements

- Challenge is submitted as pull request against this repo (fork it and create a pull request)
- The repo should include 3 screenshots under a /screenshots folder to showcase the app
- Change the README.md to explain your solution, the issues, the way you solved them...
- You can use external libraries if you think they will help you (with Carthage, Cocoapods, SPM...)
- If you're doing the Android version, please provide the final APK inside a /build folder
- You can have mock features in your implementation (mock call or delete button, ...)

# Supporting API

For all the API requests, you will have to use a test server hosting patients data in the FHIR format. You can use the following one, which already have tests data: https://synthea.mitre.org/fhir-api. Be sure to first create an account and app on their website to get an access key for the API.

You don't have to fully understand what a FHIR Patient resource is, nor understand what is exactly FHIR. You can simply refer to this page to understand the data model https://www.hl7.org/fhir/patient.html, and map only the fields you need for your test app.

# Initial results

To get the patient list (at least the first page with 30 results), you need to call the following endpoint: https://syntheticmass.mitre.org/v1/fhir/Patient?_count=30

The response will looks like:

```
{
  "resourceType": "Bundle",
  "id": "32151485-6b14-4d22-95da-795be52fc505",
  "meta": {
    "lastUpdated": "2019-03-04T20:19:54.024+00:00"
  },
  "type": "searchset",
  "link": [
    {
      "relation": "search",
      "url": "https://syntheticmass.mitre.org/v1/fhir/Patient/?_count=30"
    },
    {
      "relation": "next",
      "url": "https://syntheticmass.mitre.org/v1/fhir/..."
    }
  ],
  "entry": [
	{ Entries }
  ]
}
```

Where an Entry is like:

```
{
  "fullUrl": "https://syntheticmass.mitre.org/v1/fhir/Patient/6f7acde5-db81-4361-82cf-886893a3280c",
  "resource": {
    "address": [
      {
        "city": "Carver",
        "country": "US",
        "extension": [
          {
            "extension": [
              {
                "url": "latitude",
                "valueDecimal": 41.875179
              },
              {
                "url": "longitude",
                "valueDecimal": -70.74671500000002
              }
            ],
            "url": "http://hl7.org/fhir/StructureDefinition/geolocation"
          }
        ],
        "line": [
          "1087 Halvorson Light"
        ],
        "postalCode": "02330",
        "state": "Massachusetts"
      }
    ],
    "birthDate": "1991-02-10",
    [...]
}
```

To get the next page of result, you simply have to extract the *next url* from the *link* object, and call it again.

To get the detail of a Patient, simply extract the *full url*. With the previous example, you need to call https://syntheticmass.mitre.org/v1/fhir/Patient/6f7acde5-db81-4361-82cf-886893a3280c.

# Extra

Feel free to show off your special skill (can be UI/UX, tests, documentation...).
