# User Story: Borrow Item

**As a** logged-in user with at least one item in my lending inventory,
**I want to** borrow an available item from another user,
**So that** I can use it.

---

### Acceptance Criteria

1.  Given I am logged in and have at least one of my own items listed for lending,
    When I am viewing the details of an "Available" item that belongs to another user,
    Then I can see a "Borrow" button.
2.  Given I do not have any items listed for lending,
    When I am viewing the details of an "Available" item,
    Then the "Borrow" button is disabled or hidden, and I see a message telling me I need to list an item first.
3.  Given I click the "Borrow" button on an available item,
    Then the item's status is updated to "Lent Out" in the owner's inventory.
4.  And the item is added to my list of "Items I'm Borrowing".

---

### Technical Notes

*   This involves updating the item's record in the database to change its status and add the borrower's ID.
*   This could be implemented as a single atomic transaction in the database.
