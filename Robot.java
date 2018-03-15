/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team101.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	
	Joystick driver, operator;
	Talon driveLeft, driveRight;
	
	DoubleSolenoid gearMech;
	boolean gearTogglePressed = false;
	
	// Gamepad axis
	public static final int kGamepadAxisLeftStickX = 1;
	public static final int kGamepadAxisLeftStickY = 2;
	public static final int kGamepadAxisShoulder = 3;
	public static final int kGamepadAxisRightStickX = 4;
	public static final int kGamepadAxisRightStickY = 5;
	public static final int kGamepadAxisDpad = 6;

	// Gamepad buttons
	public static final int kGamepadButtonA = 1; // Bottom Button
	public static final int kGamepadButtonB = 2; // Right Button
	public static final int kGamepadButtonX = 3; // Left Button
	public static final int kGamepadButtonY = 4; // Top Button
	public static final int kGamepadButtonShoulderL = 5;
	public static final int kGamepadButtonShoulderR = 6;
	public static final int kGamepadButtonBack = 7;
	public static final int kGamepadButtonStart = 8;
	public static final int kGamepadButtonLeftStick = 9;
	public static final int kGamepadButtonRightStick = 10;
	public static final int kGamepadButtonMode = -1;
	public static final int kGamepadButtonLogitech = -1;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	
	driver = new Joystick(0);
	operator = new Joystick(1);
	
	driveLeft = new Talon(0);
	driveRight = new Talon(1);
	
	gearMech = new DoubleSolenoid(0, 1);
	}

	@Override
	public void autonomousInit() {
		
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		driveLeft.set(driver.getRawAxis(kGamepadAxisLeftStickY));
		driveRight.set(driver.getRawAxis(kGamepadAxisRightStickY));
		
		if(operator.getRawButton(kGamepadButtonShoulderL) && !gearTogglePressed)	
		{
			gearTogglePressed = true;
			
			if(gearMech.get() == DoubleSolenoid.Value.kOff || gearMech.get() == DoubleSolenoid.Value.kReverse)
			{
				gearMech.set(DoubleSolenoid.Value.kForward);
			}
			
			else if(gearMech.get() == DoubleSolenoid.Value.kForward)
			{
				gearMech.set(DoubleSolenoid.Value.kReverse);
			}
		}
		else if(!operator.getTrigger())
		{
			gearTogglePressed = false;
		}
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
