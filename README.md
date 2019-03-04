
![carebook](https://f3v3v80az6-flywheel.netdna-ssl.com/wp-content/uploads/2018/08/Carebook.png)

Hi dear developer!

Carebook mission is to helps you and your family stay healthy with assessments, understanding of risks, and lifestyle tips. As well, keep the information of you and your family organized in one secure place—right in your hands.

Your challenge is to build a simplified part of the app that displays the medical information of a list of patients.


# Requirements

- The app has a simple onboarding screen to explain what the app is about
- The app has a list screen, displaying patients (at least firstname and lastname) from a paginated API. 
- The app has a detail screen, showing more information about the patient (min of 4 additional fields)

# Non-functional requirements

- Challenge is submitted as pull request against this repo (fork it and create a pull request)
- The repo should include 3 screenshots under the /screenshots folder to showcase the app
- Change the README.md to explain your solution, the issues, the way you solved them...
- You can use external libraries if you think they will help you

# Supporting API

For all the API requests, you will have to use a test server hosting patients data in the FHIR format. You can use the following one, which already have tests data: http://hapi.fhir.org/. 

You don't have to fully understand what a FHIR Patient resource is, nor understand what is exactly FHIR. You can simply refer to this page to understand the data model https://www.hl7.org/fhir/patient.html, and map only the fields you need for your test app.

# Inital results

To get the patient list (at least the first page), you need to call the following endpoint: http://hapi.fhir.org/baseDstu3/Patient?_pretty=true

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
      "relation": "self",
      "url": "http://hapi.fhir.org/baseDstu3/Patient"
    },
    {
      "relation": "next",
      "url": "http://hapi.fhir.org/baseDstu3?_getpages=32151485-6b14-4d22-95da-795be52fc505&_getpagesoffset=20&_count=20&_pretty=true&_bundletype=searchset"
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
  "fullUrl": "http://hapi.fhir.org/baseDstu3/Patient/1520945",
  "resource": {
    "resourceType": "Patient",
    "id": "1520945",
    "meta": {
      "versionId": "1",
      "lastUpdated": "2019-02-28T21:58:09.437+00:00"
    },
    "text": {
      "status": "generated",
      "div": "<div xmlns=\"http://www.w3.org/1999/xhtml\"><div class=\"hapiHeaderText\">Don <b>JUAN </b></div><table class=\"hapiPropertyTable\"><tbody></tbody></table></div>"
    },
    "name": [
      {
        "family": "Juan",
        "given": [
          "Don"
        ]
      }
    ]
  },
  "search": {
    "mode": "match"
  }
}
```

To get the next page of result, you simply have to extract the *next url* from the *link* object, and call it again. With the previous example, you need to call http://hapi.fhir.org/baseDstu3?_getpages=32151485-6b14-4d22-95da-795be52fc505&_getpagesoffset=20&_count=20&_pretty=true&_bundletype=searchset.

To get the detail of a Patient, simply extract the *full url*. With the previous example, you need to call http://hapi.fhir.org/baseDstu3/Patient/1520945.

