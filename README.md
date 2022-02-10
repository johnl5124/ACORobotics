# ACORobotics - Dissertation Project - Ant Colony Optimisation using Robotics in Dangerous Places
Updated: 26/01/2022

## ALPHA 1.0
- [x] Movement (L, R, F, B) (22/01/2022)
- [x] Ultrasound (22/01/2022)
- [x] Ultrasond-based movement:(26/01/2022)
  - [x] Stopping based on proxmity
  - [x] Choosing direction or backing out from dead end 
- [ ] Camera implementation:
	- [x] Take a photo
	- [ ] Take a video of movement
	- [ ] Pick up QR code
	- [ ] Incorperate QR pickup inside maze navigation

## BETA 2.0
- [ ] Node dropping - N/A
	- [ ] Node weightings - N/A
- [ ] Planar graph implementation - N/A
- [ ] Short-path implementation - N/A
- [ ] Using camera to aid navigation - TBC

## Testing
- [ ] ALPHA testing
	- [ ] Move through maze unaided 
- [ ] BETA testing

## QoL, Future Updates and Extras
- [ ] User-input GPIO reset
- [ ] Ultrasonic burst and movement combined into one so they can run alongside (threading)

## Refactoring
- [x] Integrate into Maven and sort dependacies
- [ ] Seperate movement into own class
- [ ] Seperate ultrasound into own class
- [ ] Create Ant Robot builder class
- [ ] Create initialisation class

## Dependencies
- [Pi4j](https://pi4j.com/1.2/download.html)
- [BoofCV](https://github.com/lessthanoptimal/BoofCV)
- [JRPiCam](https://github.com/Hopding/JRPiCam)
