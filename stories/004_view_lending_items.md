# User Story: View Lending Items

**As a** logged-in user,
**I want to** see a list of all the items I have made available for lending,
**So that** I can manage them.

---

### Acceptance Criteria

1.  Given I am logged in,
    When I navigate to my profile or inventory screen,
    Then I can see a list of all the items I am lending.
2.  For each item in the list, I can see its Name and current status ("Available", "Lent Out").
3.  Given I have no items in my lending inventory,
    When I view my inventory,
    Then I see a message indicating that I haven't added any items yet.

---

### Technical Notes

*   The list should be populated by fetching the user's items from the database.
