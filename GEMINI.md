# Gemini Development Workflow

This document outlines the development process for this project. Please adhere to these guidelines to ensure a smooth and efficient workflow.

## Core Principles

*   **Iterative Development:** We build software in small, incremental steps.
*   **Commit regularly:** Commit your code regularly and make sure that your code is working as expected.
*   **Run the tests frequently:** Run the tests frequently to ensure that your code is working as expected.
*   **Document your work:** Document your work in a way that is understandable by other developers.
*   **Clean Code:** Write clean, readable, and maintainable code. Follow the principles of "Clean Code" by Robert C. Martin.
*   **Test-Driven Development (TDD):** Write tests before you write production code. This ensures that your code is testable and that you have a clear understanding of the requirements.

## Technology Stack

*   **Language:** Kotlin
*   **Framework:** Kotlin Multiplatform
*   **Architecture:** Clean Architecture
*   **Testing:** JUnit 5
*   **Version Control:** Git
*   **Continuous Integration:** GitHub Actions
*   **Code Style:** Kotlin Style Guide
*   **Documentation:** Markdown

## Development Process

1.  **Understand the Task:** Before you start coding, make sure you understand the requirements of the task.
2.  **Plan your work:** Create a plan for your work and save it in the `/plans` directory. The plan should be a markdown file with a descriptive name.
3.  **Check wether all prerequisites are met:** Make sure that all prerequisites are met.
4.  **Add ToDos for Human User** Mark all ToDos that have to be done by the human user in the readme.
5.  **Update Kanban Board:** Update the Kanban board with the status of your task.
6.  **Create a Branch:** Create a new branch for your work.
7.  **Write Tests:** Start by writing a failing test that describes the desired behavior.
8.  **Write Code:** Write the minimum amount of code required to make the test pass.
9.  **Refactor:** Refactor your code to improve its design and readability.
10.  **Repeat:** Repeat this process until the task is complete.
11.  **Move the task to Done:** Move the task to the "Done" column in the Kanban board.
12.  **Run Build and Tests:** Always run the build and tests to ensure the changes are valid and do not break existing functionality before pushing.
13.  **Commit and Push Changes:** Automatically commit and push all changes to the current branch after completing a task or a significant part of it.
14.  **Submit a Pull Request:** Submit a pull request to merge your code into the `main` branch

## Requirements and Task Management

This project uses a lightweight, markdown-based system for managing requirements and tracking progress.

*   **Product Requirements Document (`PRODUCT.md`):** This file contains the high-level vision, target audience, and feature overview for the product.
*   **Roadmap (`ROADMAP.md`):** This file outlines the planned release versions and the major features scoped for each release.
*   **User Stories (`/stories`):** Detailed, implementable user stories for the current release are defined in individual markdown files within the `/stories` directory. Each story includes acceptance criteria and technical notes.
*   **Kanban Board (`KANBAN.md`):** A text-based Kanban board is used to provide a quick overview of the status of each user story for the current release. Stories are moved from "To Do" to "In Progress" and finally to "Done".

## Error Handling

*   If you encounter an error, try to fix it yourself first.
*   Use the debugger to step through your code and understand the cause of the error.
*   If you are unable to fix the error, ask for help.
*   If there are steps that have to be taken by the user, communicate them clearly

## Testing

*   All existing tests must pass before you can merge your code into the `main` branch.
*   Run the tests frequently to ensure that you haven't introduced any regressions.

## Version Control (Git)

*   **Git:** We use Git for version control.
*   **Branching:** Create a new branch for each new feature or bug fix.
*   **Small Commits:** Make small, atomic commits. Each commit should represent a single logical change.
*   **Commit Messages:** Write clear and concise commit messages.
*   **Main Branch:** The `main` branch should always be in a releasable state. Do not push broken code to the `main` branch. We follow a Trunk-Based Development approach.
*   **User Approval:** Before merging any code changes to the `main` branch, you must ask for user approval.

## Architecture

*   **Clean Architecture:** We use Clean Architecture to separate the concerns of our application.
*   **Documentation:** Document all significant architecture decisions in the `ARCHITECTURE.md` file.

## UI Development

As a command-line agent, Gemini cannot visually create or test user interfaces. Instead, Gemini will create "skeleton" views. This involves:

1.  **Creating the UI Structure:** Writing the Jetpack Compose code (`@Composable` functions) with the necessary components like `TextField`, `Button`, and `Text`.
2.  **Connecting the Logic:** Binding these components to the `ViewModel`, ensuring that user input is captured, actions are triggered, and the UI reacts to state changes (like showing a loading indicator or an error message).

The result will be a functional but unstyled UI. A human developer is then responsible for applying the visual design (colors, typography, layout, etc.) on top of the functional skeleton.

## Project Overview

*   For a project overview and initial setup instructions, please refer to the `README.md` file.
