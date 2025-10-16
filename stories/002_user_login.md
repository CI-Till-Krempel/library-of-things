# User Story: User Login

**As a** registered user,
**I want to** log in with my email and password,
**So that** I can access my account.

---

### Acceptance Criteria

1.  Given I am a registered user and I am on the login screen,
    When I enter my correct email and password,
    Then I am successfully logged in and taken to the main screen.
2.  Given I am on the login screen,
    When I enter an incorrect email or password,
    Then I see an error message indicating invalid credentials.
3.  Given I am already logged in,
    When I reopen the app,
    Then I should remain logged in without needing to enter my credentials again.

---

### Technical Notes

*   Use Firebase Authentication for email/password sign-in.
*   Implement session persistence to keep the user logged in across app launches.
