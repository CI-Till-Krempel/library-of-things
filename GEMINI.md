# Gemini Development Workflow

This document outlines the development process for this project. Please adhere to these guidelines to ensure a smooth and efficient workflow.

## Core Principles

*   **Iterative Development:** We build software in small, incremental steps.
*   **Clean Code:** Write clean, readable, and maintainable code. Follow the principles of "Clean Code" by Robert C. Martin.
*   **Test-Driven Development (TDD):** Write tests before you write production code. This ensures that your code is testable and that you have a clear understanding of the requirements.

## Technology Stack

*   **Language:** Kotlin
*   **Framework:** Kotlin Multiplatform
*   **Architecture:** Clean Architecture

## Development Process

1.  **Understand the Task:** Before you start coding, make sure you understand the requirements of the task.
2.  **Plan your work:** Create a plan for your work and save it in the `/plans` directory. The plan should be a markdown file with a descriptive name.
3.  **Write Tests:** Start by writing a failing test that describes the desired behavior.
4.  **Write Code:** Write the minimum amount of code required to make the test pass.
5.  **Refactor:** Refactor your code to improve its design and readability.
6.  **Repeat:** Repeat this process until the task is complete.

## Error Handling

*   If you encounter an error, try to fix it yourself first.
*   Use the debugger to step through your code and understand the cause of the error.
*   If you are unable to fix the error, ask for help.

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

## Project Overview

*   For a project overview and initial setup instructions, please refer to the `README.md` file.
