# User Story: Return Item

**As a** logged-in user,
**I want to** mark an item I've borrowed as "returned",
**So that** I can end the borrowing period.

---

### Acceptance Criteria

1.  Given I am viewing the list of items I am currently borrowing,
    When I select an item,
    Then I see a "Return Item" button.
2.  Given I click the "Return Item" button,
    Then the item is removed from my "Items I'm Borrowing" list.
3.  And the item's status is updated to "Available" in the owner's inventory, and the borrower ID is removed.

---

### Technical Notes

*   This involves updating the item's record in the database to change its status back to "Available" and clear the borrower ID field.
*   For the MVP, the return is instant. A future version might involve confirmation from the owner.
