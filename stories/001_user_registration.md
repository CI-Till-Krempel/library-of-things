# User Story: User Registration

**As a** new user,
**I want to** create an account using my email and a password,
**So that** I can log in and use the application.

---

### Acceptance Criteria

1.  Given I am on the registration screen,
    When I enter a valid email and a password of at least 6 characters, and confirm the password,
    Then my account is created successfully.
2.  Given I am on the registration screen,
    When I try to register with an email that is already in use,
    Then I see an error message indicating the email is already taken.
3.  Given I am on the registration screen,
    When I enter an invalid email format,
    Then I see an error message about the invalid email.
4.  Given I am on the registration screen,
    When my password is less than 6 characters,
    Then I see an error message about the password length.
5.  Given I am on the registration screen,
    When my password and confirmation password do not match,
    Then I see an error message that the passwords do not match.

---

### Technical Notes

*   Use Firebase Authentication for email/password registration.
*   Upon successful registration, the user should be automatically logged in.
