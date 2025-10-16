# User Story: Add Lending Item

**As a** logged-in user,
**I want to** add an item to my personal lending inventory,
**So that** I can make it available for others to borrow.

---

### Acceptance Criteria

1.  Given I am logged in,
    When I navigate to my personal lending inventory and choose to add a new item,
    Then I am presented with a form to enter the item's details (Name, Description, Category).
2.  Given I am filling out the new item form,
    When I provide all the required details and save the item,
    Then the item is added to my lending inventory with a status of "Available".
3.  Given I am filling out the new item form,
    When I leave a required field (e.g., Name) blank,
    Then I see an error message and the item is not saved.

---

### Technical Notes

*   The item data should be stored in a database (e.g., Firestore) and associated with the user's ID.
*   For the MVP, "Category" can be a simple text field.
*   Image upload can be added in a future version.
