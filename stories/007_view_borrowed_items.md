# User Story: View Borrowed Items

**As a** logged-in user,
**I want to** see a list of items I am currently borrowing,
**So that** I can keep track of them.

---

### Acceptance Criteria

1.  Given I am logged in,
    When I navigate to my profile or borrowed items screen,
    Then I can see a list of all the items I am currently borrowing from others.
2.  For each item in the list, I can see its Name and the name of the lender.
3.  Given I am not borrowing any items,
    When I view my borrowed items list,
    Then I see a message indicating that I am not currently borrowing anything.

---

### Technical Notes

*   The list should be populated by querying the database for items that have the current user's ID as the borrower.
