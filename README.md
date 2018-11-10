# Kappa (formerly PPP)

This is yet another SCRUM planning-poker-app for Android that at least tries to not be ugly for once or ported from iOS. 
I will use this app to try out some new things, like libraries, architectures, designs and so on.

# Done:
- Written in kotlin (still improving my kotlin-skills though)
- Basic cards, including some special unicode-characters (because unicode is cool)
- Multi-row-recycler-view (overview/card selection) to view-pager (fullscreen-cards)
- Single-activity approach
- CoordinatorLayout with conditions
- Autosizing TextView (removed in 1.0.0 since it is buggy, see #1)
- Shared-element-transitions (cards) between activities (removed in 1.0.0 and not established with fragment-transitions yet, see #2)
- Adaptive launcher icon
- An awesome purple primary color ;)

# Backlog:
- More decks (selection via settings)
- Support at least a second theme/color-scheme (selection via settings)
- MaterialDesign2 when available
- More awesome animations/transitions, like a FAB-onClick-inflation or something
- Launcher icon with shadow
- Maybe try out android architecture components / jetpack stuff
- Maybe card-flip-feature
- ...
