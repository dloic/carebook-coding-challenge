//
//  PatientListTableViewCell.swift
//  CareBookChallenge
//
//  Created by Shahab Chaouche on 2019-04-30.
//  Copyright © 2019 Shahab Chaouche. All rights reserved.
//

import UIKit

class PatientListTableViewCell: UITableViewCell {

    
    @IBOutlet weak var lblPatientID: UILabel!
    @IBOutlet weak var lblName: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
