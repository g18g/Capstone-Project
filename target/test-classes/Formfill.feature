Feature: Fill up the forms

@Healthy_Aging
Scenario: User is filling Sign Up for Healthy Aging  form
Given To "Healthy Aging" page
When Fill in the form with "John", "Doe", "1234567891011", "john.doe@example.com" 
And Select "Tai Chi for Arthritis" Program checkbox
And Select "Santa Rosa" from the country radio buttons
And Select "No preference" radiobox
And Select Email Notification Checkbox and click on submit
Then Should see Form Successfully sumbitted message


@Covering_Florida
  Scenario: User is filling the Find Local Help form
   Given To "Covering Florida" page
    When User Enters the details
    Then User should see all the places

 
