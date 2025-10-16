# User Story: Search for Item

**As a** logged-in user,
**I want to** search for available items by name,
**So that** I can find something to borrow.

---

### Acceptance Criteria

1.  Given I am logged in,
    When I am on the main or search screen,
    Then I see a search bar.
2.  Given I have entered text into the search bar,
    When I execute the search,
    Then I see a list of all "Available" items from other users whose names contain the search text.
3.  Given my search returns no results,
    When the search is complete,
    Then I see a message indicating that no items were found.

---

### Technical Notes

*   The search should query the master item collection in the database.
*   The search should only return items with a status of "Available".
*   The search should exclude items that belong to the current user.
