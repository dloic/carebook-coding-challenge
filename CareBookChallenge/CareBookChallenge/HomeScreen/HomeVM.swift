//
//  HomeVM.swift
//  CareBookChallenge
//
//  Created by Shahab Chaouche on 2019-04-29.
//  Copyright © 2019 Shahab Chaouche. All rights reserved.
//

import UIKit

class HomeVM: NSObject {

    let networkManager = NetworkManager()

    var patientList: [Entry] = []
    var nextPageUrl: String = ""
    
    func getPatientList(url: String, completion: @escaping (_ success: Bool) -> Void) {
        
        networkManager.getPatientList(url: url) { entries, nextPageUrl in
            self.patientList = entries
            self.nextPageUrl = nextPageUrl
            completion(true)
        }
        
    }
    
    func loadMorePatients(url: String, completion: @escaping (_ success: Bool) -> Void) {
        
        networkManager.getPatientList(url: url) { entries, nextPageUrl in
            self.patientList.append(contentsOf: entries)
            self.nextPageUrl = nextPageUrl
            completion(true)
        }
        
    }
    
}
