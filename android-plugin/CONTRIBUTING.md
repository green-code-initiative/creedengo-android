Contributing Guidelines
=======================

Contributions are welcome!

**Before spending lots of time on something, ask for feedback on your idea first.**
Contact us at <contact@green-code-initiative.org> to be sure that we are interested with your contribution idea.

To avoid frustration, please discuss before submitting any contributions.

General Coding recommendations
----------------------

### Make your changes minimal

The less code is modified, the easier to review, and it makes your contribution more likely to be accepted.
This means that your commits should be atomic and have a single purpose. Formatting modifications should not clutter
your changes in order to make the reviewer's job easier.
  
### Test, test, and test

Coding is the easy part. If you want your contribution to be accepted, demonstrate it solves an issue by providing the unit test it solves (that would have failed before).

### Clean commit history

To ease the review, please have a clean, minimal history of commits in your pull request. Your commits should have a single purpose.
This will help to make your contribution accepted as we like to keep a clean linear history and prefer rebase over merging commits.

Plugin-specific recommendations
----------------------

### Commit to rule development
Choose a rule within the [table of unimplemented android-specific rules](RULES.md). When submitting a *Pull Request* with your code, give it the following name: `[<id here>] <name of the rule here>`, along with both labels `android` and `rule` to ease the work of reviewers.

### Naming conventions
Before submitting an Android-specific SonarQube custom rule, please take a look at our [naming conventions](https://doc.rules.creedengo.io/#how-to-specify-rules). 

### Avoid doing things the hard way
Ideally, take also a look at the [helpers functions](./src/main/java/io/creedengo/java/checks/helpers).
