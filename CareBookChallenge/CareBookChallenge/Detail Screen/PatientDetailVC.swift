//
//  PatientDetailViewController.swift
//  CareBookChallenge
//
//  Created by Shahab Chaouche on 2019-04-30.
//  Copyright © 2019 Shahab Chaouche. All rights reserved.
//

import UIKit

class PatientDetailVC: UIViewController {

    var patientDetails: Entry?
    
    @IBOutlet weak var firstNameLbl: UILabel!
    @IBOutlet weak var lastNameLbl: UILabel!
    @IBOutlet weak var dateOfBirthLbl: UILabel!
    @IBOutlet weak var genderLbl: UILabel!
    @IBOutlet weak var streetNameLbl: UILabel!
    @IBOutlet weak var cityLbl: UILabel!
    @IBOutlet weak var countryLbl: UILabel!
    @IBOutlet weak var postalCodeLbl: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        firstNameLbl.text = patientDetails?.resourse.name.given
        lastNameLbl.text = patientDetails?.resourse.name.familyName
        dateOfBirthLbl.text = patientDetails?.resourse.birthdate
        genderLbl.text = patientDetails?.resourse.gender
        streetNameLbl.text = patientDetails?.resourse.address.streeAddress
        cityLbl.text = patientDetails?.resourse.address.city
        countryLbl.text = patientDetails?.resourse.address.country
        postalCodeLbl.text = patientDetails?.resourse.address.postalCode

    }
    


}
