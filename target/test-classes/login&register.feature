  
@whec
Feature: Registration and login on WHEC Website

@registration
Scenario: User Registration
Given to "MY ACCOUNT" page
When Details "<username>", "<emailid>", "<password>" into the form
And  click register
Then redirected to "<username>" dashboard

Examples: Registration Example
 | username | emailid             | password       |
 | Jason789  | jason178@gmail.com   | Jason19123!    |
 
@login
Scenario: User Login
Given to "MY ACCOUNT" page
When details "<username>", "<password>" into the form
And click login
Then redirected to "<username>" login dashboard

Examples: Login Example
 | username | password     |
 | Mike24   | Mike24123!   |

@Already_exist 
Scenario: Already exist in register form
    Given to "MY ACCOUNT" page
    When Details "<username>", "<emailid>", "<password>" into the form
    And click register
    Then User should get account already exist error

Examples: User already exist Registration Example 
 | username | emailid             | password       |
 | Mike12   | Mike24@gmail.com    | Mike24123!    |
 
@forgot_password
Scenario: Forgot password 
Given to "MY ACCOUNT" page
When Click on forgot password
And Enter the "Mike24" username and click on submit
Then should see password reset link sent to email message
 