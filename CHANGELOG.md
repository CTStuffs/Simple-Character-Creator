# Changelog
All notable changes to this project will be documented in this file.

## [1.0.3] - 2018-6-04
- Added a new GUI object specifically for the six stats, as well as a button linking it to it.
- Added a 'New' option in the File menu. When clicked, it resets all the fields across all GUI objects.
- Added scroll bars to all the big text fields in all the GUIs.

### Changed
- Changed the Layout of all GUI objects to MigLayout

## [1.0.2] - 2018-6-03

### Changed
- Fully fleshed out the character file loading process
- Cleaned up some more parts of the code


## [1.0.1] - 2018-6-02

### Added
- Added borders to the Description and Other Description of the Main GUI.
- Added a debug option in the Help options. Will likely be removed later
- A warning message will now flash if the user tries to save when any fields are left blank.

### Changed
- Added a very barebones loading function. It doesn't load anything yet, rather prints the filename to console. Will be worked on later.
- Fleshed out the save file function. It now saves text files by default and said text files have the correct content in them.
- Fleshed out the Character Power GUI. The user can add talents/techniques and have them saved to the text files when the option is chosen.
- Fixed miscellaneous bugs relating to the text field
- Cleaned up the code and added comments


## [1.0.0] - 2018-5-30
### Added
- Basic structure of GUI.
- Fields for all the character components
- A seperate GUI for the character poweres
- A save file function
- Objects and enums representing a character
