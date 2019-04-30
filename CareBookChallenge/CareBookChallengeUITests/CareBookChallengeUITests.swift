//
//  CareBookChallengeUITests.swift
//  CareBookChallengeUITests
//
//  Created by Shahab Chaouche on 2019-04-29.
//  Copyright © 2019 Shahab Chaouche. All rights reserved.
//

import XCTest

class CareBookChallengeUITests: XCTestCase {

    func testAppFlow() {
        
        let app = XCUIApplication()
        app.launchArguments = ["enable-testing"]
        app.launch()
        
        //Close Onboarding
        let onboardingDoneButton = app.navigationBars["CareBookChallenge.OnBoardingVC"].buttons["Done"]
        XCTAssert(onboardingDoneButton.waitForExistence(timeout: 10))
        onboardingDoneButton.tap()
        
        //Click First Cell
        let cellCount = app.tables.cells.count
        if cellCount != 0 {
            let firstCell = app.tables.element(boundBy: 0).cells.element(boundBy: 0)
            XCTAssert(firstCell.waitForExistence(timeout: 10))
            firstCell.tap()
        }
        
        //Check first name Label
        let firstNameLabel = app.staticTexts["firstName"]
        XCTAssert(firstNameLabel.waitForExistence(timeout: 10))
        
        //Go Back to List View
        let backButton = app.navigationBars["CareBookChallenge.PatientDetailVC"].buttons["Back"]
        XCTAssert(backButton.waitForExistence(timeout: 10))
        backButton.tap()

    }

}
