//
//  Entry.swift
//  CareBookChallenge
//
//  Created by Shahab Chaouche on 2019-04-29.
//  Copyright © 2019 Shahab Chaouche. All rights reserved.
//

struct PatientList {
    let nextPage: String
    let previousePage: String
    let entries: [Entry]

}

struct Entry {
    let fullUrl: String
    let resourse: Resource
    
    init(jsonData: [String: Any]) {
        fullUrl = jsonData["fullUrl"] as? String ?? ""
        resourse = Resource.init(resourseData: jsonData["resource"] as? [String: Any] ?? [:])
    }
}

struct Resource {
    let id: String
    let resourceType: String
    let name: Name
    let gender: String
    let birthdate: String
    let address: Address
    
    
    init(resourseData: [String: Any]) {
        
        id = resourseData["id"] as? String ?? ""
        resourceType = resourseData["resourceType"] as? String ?? ""
        name = Name.init(nameDict: resourseData["name"] as? [[String: Any]] ?? [])
        gender = resourseData["gender"] as? String ?? ""
        birthdate = resourseData["birthDate"] as? String ?? ""
        address = Address.init(addressDict: resourseData["address"] as? [[String: Any]] ?? [])
    }
    
}

struct Name {
    let given: String
    let familyName: String
    
    init(nameDict: [[String: Any]]) {
        given = nameDict.map { $0["given"] }.first as? String ?? ""
        familyName = nameDict.map { $0["family"] }.first as? String ?? ""
    }
}

struct Address {
    let streeAddress: String
    let city: String
    let postalCode: String
    let country: String
    
    init(addressDict: [[String: Any]]) {
        streeAddress = addressDict.map { $0["text"] }.first as? String ?? ""
        city = addressDict.map { $0["city"] }.first as? String ?? ""
        postalCode = addressDict.map { $0["postalCode"] }.first as? String ?? ""
        country = addressDict.map { $0["country"] }.first as? String ?? ""
    }
    
    
}
