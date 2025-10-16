# User Story: Borrower Initiates Return

**As a** borrower,
**I want to** inform the lender that I have returned their item,
**So that** we can complete the transaction.

---

### Acceptance Criteria

1.  Given I am viewing the list of items I am currently borrowing,
    When I select an item,
    Then I see an "I've Returned This" button.
2.  Given I click the "I've Returned This" button,
    Then the item's status is updated to "Pending Return".
3.  And the item remains in my "Items I'm Borrowing" list, but is clearly marked as "Pending Return".

---

### Technical Notes

*   This action changes the item's status in the database.
*   It does not make the item available again. The lender must confirm the return first.
*   This is the first step in the two-step return process.
