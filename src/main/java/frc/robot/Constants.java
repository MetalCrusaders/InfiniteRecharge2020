/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Motors CAN
    public static final int WHEEL_MOTOR = 0;
    public static final int DRIVE_LEFT_VICTORSPX0 = 1;
    public static final int DRIVE_LEFT_VICTORSPX1 = 2;
    public static final int DRIVE_RIGHT_VICTORSPX0 = 4;
    public static final int DRIVE_RIGHT_VICTORSPX1 = 3;
    
    //Motors PWM
    public static final int SHOOTER_RIGHT_MOTOR = 7;
    public static final int SHOOTER_LEFT_MOTOR = 6;
    public static final int INTAKE_MOTOR = 4;
    public static final int CLIMB_MOTOR = 5;

    public static final int INDEX_MOTOR1 = 2;
    public static final int INDEX_MOTOR2 = 3;

    //Encoders
    public static final int ENCODER_PORT0 = 0;
    public static final int ENCODER_PORT1 = 1;
    public static final int ENCODER_PORT2 = 4;
    public static final int ENCODER_PORT3 = 5;

    //Solenoids
    public static final int CLIMB_SOLENOID_DEPLOY = 0;
	public static final int CLIMB_SOLENOID_RETRACT = 1;
	public static final int INTAKE_SOLENOID_DEPLOY = 2;
	public static final int INTAKE_SOLENOID_RETRACT = 3;

    // Controllers
    public static final int kController1 = 0;
    public static final int kController2 = 1;

}
