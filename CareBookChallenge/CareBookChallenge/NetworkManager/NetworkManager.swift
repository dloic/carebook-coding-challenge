//
//  NetworkManager.swift
//  CareBookChallenge
//
//  Created by Shahab Chaouche on 2019-04-29.
//  Copyright © 2019 Shahab Chaouche. All rights reserved.
//

import UIKit
import Alamofire


class NetworkManager: NSObject {

    func getPatientList(url: String, completion: @escaping (_ patientList: [Entry], _ nextURLString: String) -> Void) {
        
        Alamofire.request(url, method: .get)
            .validate()
            .responseJSON(completionHandler: { (response) in
                guard response.result.isSuccess else {
                    print("Error fetching data")
                    return
                }
                
                guard let value = response.result.value as? [String: Any],
                    let links = value["link"] as? [[String: Any]],
                    let entry = value["entry"] as? [[String: Any]] else {
                    print("incorrect data received")
                    return
                }
                
                let nextURL = links.filter { $0["relation"] as? String == "next" }.first?["url"] as? String ?? ""
                let entries = entry.map { Entry(jsonData: $0) }
                completion(entries, nextURL)
                
            })
    }
    
    func getPatientDetails(url: String, completion: @escaping (_ patientDetails: Entry) -> Void) {
        
        Alamofire.request(url, method: .get)
            .validate()
            .responseJSON(completionHandler: { (response) in
                guard response.result.isSuccess else {
                    print("Error fetching data")
                    return
                }
                
                guard let value = response.result.value as? [String: Any] else {
                        print("incorrect data received")
                        return
                }
                
                let entry = Entry(jsonData: value)
                completion(entry)
                
            })
    }

}
