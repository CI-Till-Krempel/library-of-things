# User Story: Lender Confirms Return

**As a** lender,
**I want to** confirm that a borrowed item has been returned to me in good condition,
**So that** the item becomes available again in my inventory and the borrowing record is complete.

---

### Acceptance Criteria

1.  Given one of my items has been marked as "Pending Return" by the borrower,
    When I view the item in my lending inventory,
    Then I see a "Confirm Return" button.
2.  Given I click "Confirm Return",
    Then the item's status is updated to "Available" in my inventory.
3.  And the item is fully removed from the borrower's list of borrowed items.
4.  And the borrower ID is removed from the item record in the database.

---

### Technical Notes

*   This finalizes the transaction. The item is now available for others to borrow.
*   This action should be the final step before allowing the lender and borrower to rate each other in a future version.
