 @healthPrograms
Feature: Navigation and Access to Health Programs
  As a user of the WHEC website,
  I want to navigate to different health programs
  So that I can access relevant content and information.

  @QuitTobacco
 		Scenario: Navigate to the Smoking Cessation Program
    Given click on "PROGRAMS" in the main menu
    When select "TOBACCO" from the dropdown and click on "QUIT TOBACCO"
    Then  should be redirected to the "Quit Tobacco" page
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   @Training
   	Scenario: Navigate to Training Program
    Given click on "PROGRAMS" in the main menu
    When select "TOBACCO" from the dropdown and click on "TRAINING"
    Then  should be redirected to the "Training" page