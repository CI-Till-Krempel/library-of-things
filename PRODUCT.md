# Product Requirements Document: Library of Things

## 1. Introduction

The "Library of Things" is a peer-to-peer sharing application for communities. It enables members to both borrow items they need and lend out items they own but don't frequently use. The core principle is "give a thing, take a thing": to borrow from the community, a user must also contribute by making at least one of their own items available to lend. This fosters a circular, community-driven economy, reduces consumer waste, and gives people access to a wide variety of items.

## 2. Target Audience

*   **Community Members:** Individuals within a defined community who want to borrow items instead of buying them, and are willing to lend their own items to others.
*   **Community Administrators (Optional):** In some community setups, an admin may be needed to moderate content and users.

## 3. User Roles and Permissions

*   **Member (Standard User):**
    *   Can add, edit, and remove items from their personal "lending" inventory.
    *   Can browse and search the master catalog of all items being lent by the community.
    *   Can borrow an available item from another user (contingent on having at least one item listed for lending).
    *   Can manage return processes for their own items that have been borrowed.
    *   Can view their own profile, which includes a list of items they are currently borrowing and a list of items they are currently lending out.
*   **Administrator:**
    *   Has all the permissions of a Member.
    *   Can view all users and their inventories.
    *   Can moderate and remove inappropriate items from the master catalog.
    *   Can resolve disputes between users.

## 4. Core Features

### 4.1. Personal Lending Inventory

*   **Add/Edit/Remove Items:** Users must be able to manage a list of items they are willing to lend. For each item, they can provide:
    *   Name
    *   Description
    *   Category
    *   A picture of the item
*   The user can set an item's status (e.g., "Available", "Lent Out", "Unavailable").

### 4.2. Community Item Catalog

*   **Browse Items:** Users can browse a master catalog composed of all "Available" items from every user's lending inventory.
*   **Search & Filter:** Users can search for items by name or description and filter by category.
*   **Item Details:** Each item's detail page will show its properties, the lender's username (or profile), and its current borrowing status.

### 4.3. Borrowing and Returning

*   **Borrowing Prerequisite:** A user must have at least one item listed in their "Personal Lending Inventory" before they are permitted to borrow an item.
*   **Borrowing Request:** A user can request to borrow an available item. This may notify the item's owner for approval (feature for a later version). For MVP, we can assume the borrow is instant.
*   **Checkout:** The item's status changes to "Lent Out" in the owner's inventory and it appears in the borrower's "items I've borrowed" list.
*   **Returning:** The borrower and lender coordinate the return. Once returned, the borrower marks it as "Returned" in their list, and the owner confirms the return, making the item "Available" again in their inventory.

### 4.4. User Profiles

*   **User Registration & Login:** Users must create an account and log in.
*   **Profile View:** The user profile is central and must clearly display:
    *   **Items I'm Lending:** A list of their own items, and their current status (Available, Lent Out).
    *   **Items I'm Borrowing:** A list of items they have borrowed from others.

## 5. Technical Requirements & Constraints

*   **Platform:** Kotlin Multiplatform (Android, iOS, Web).
*   **Architecture:** Clean Architecture.
*   **Data Storage:** A database is required to store user data, personal inventories, and transaction records.

## 6. Assumptions

*   Users will handle the physical exchange of items themselves. The app is for tracking and discovery.
*   The initial version will not include a reservation system, owner approval for borrowing, or a reputation/rating system.

## 7. MVP Scope

The MVP will focus on the core loop: a user lists an item, and can then borrow an item from someone else.

*   **User Accounts (Firebase Auth):**
    *   Users can sign up and log in via email/password.

*   **Personal Lending Inventory (CRUD):**
    *   A user can add, view, update, and delete items in their personal lending inventory. This is a prerequisite for borrowing.

*   **Item Search:**
    *   A simple text-based search to find items by name in the community catalog.

*   **Borrowing and Returning Process:**
    *   **Checkout:** A "Borrow" button on an item's detail page. This is only enabled if the user has at least one item in their own lending inventory. Clicking it moves the item to the borrower's "borrowed" list and updates its status.
    *   **Return:** A "Return" button in the borrower's list. Clicking it removes the item from their list and updates its status to "Available" in the owner's inventory.

## 8. Future Versions

Beyond the MVP, the following features are envisioned to enhance the platform's functionality and user experience:

*   **User Reputation System:** A five-star rating system for both lenders and borrowers to build trust within the community. Users can rate each other after a transaction is complete.
*   **In-App Messaging:** A secure messaging system to allow users to communicate directly to ask questions about items or coordinate pickups and returns.
*   **Item Reservation:** The ability for a user to reserve an item that is currently lent out. The user would be notified when the item becomes available.
*   **Map View:** An alternative way to browse for items, showing their approximate location on a map to facilitate local borrowing.
*   **Insurance for High-Value Items:** Integration with a third-party insurance provider to offer optional, short-term insurance for expensive items, giving lenders greater peace of mind.
*   **Integration with Local Sharing Platforms:** APIs to connect with other local sharing initiatives or platforms, creating a broader network of shared goods.
