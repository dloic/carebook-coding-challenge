//
//  HomeVC.swift
//  CareBookChallenge
//
//  Created by Shahab Chaouche on 2019-04-29.
//  Copyright © 2019 Shahab Chaouche. All rights reserved.
//

import UIKit
import Alamofire

class HomeVC: UIViewController {

    @IBOutlet weak var tableView: UITableView!
    
    lazy var refreshControl: UIRefreshControl = {
        let refreshControl = UIRefreshControl()
        refreshControl.addTarget(self, action: #selector(self.refreshData(_:)),for: UIControl.Event.valueChanged)
        refreshControl.tintColor = UIColor.purple
        return refreshControl
    }()
    let viewModel = HomeVM()
    let initalPageUrl = "http://hapi.fhir.org/baseDstu3/Patient?_pretty=true"
    let cellID = "PatientListTableViewCell"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.register(UINib(nibName: cellID, bundle: nil), forCellReuseIdentifier: cellID)
        self.tableView.addSubview(refreshControl)

        viewModel.getPatientList(url: initalPageUrl) { (success) in
            if success {
                self.tableView.reloadData()
            }
        }
        
        showOnboarding()
    }
    
    func showOnboarding() {
        
        let vc = UIStoryboard.init(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "Onboarding")
        
        self.present(vc, animated: true, completion: nil)
        
    }
    
    @objc func refreshData(_ refreshControl: UIRefreshControl) {
        
        viewModel.getPatientList(url: initalPageUrl) { (success) in
            if success {
                self.tableView.reloadData()
                refreshControl.endRefreshing()
            }
        }
    }
}

extension HomeVC: UITableViewDataSource, UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.patientList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellID) as? PatientListTableViewCell else { return UITableViewCell() }
        
        let patientDetails = viewModel.patientList[indexPath.row].resourse
        
        cell.lblPatientID.text = patientDetails.id
        cell.lblName.text = "\(patientDetails.name.given) \(patientDetails.name.familyName)"
        
        if indexPath.row == viewModel.patientList.count - 1 {
            self.loadMorePatients()
        }
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        let patient = viewModel.patientList[indexPath.row]
        
        guard let detailVC = self.storyboard?.instantiateViewController(withIdentifier: "PatientDetailVC") as? PatientDetailVC else { return }
        detailVC.patientDetails = patient
        self.navigationController?.pushViewController(detailVC, animated: true)
       
    }
    
    func loadMorePatients() {
        
        viewModel.loadMorePatients(url: viewModel.nextPageUrl) { (success) in
            if success {
                self.tableView.reloadData()
            }
        }
        
    }
    
    
}

