/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveArcade;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ColorSensor;
import frc.robot.commands.ColorRead;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Intake;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.DriveArcadeAuto50;
import frc.robot.commands.DriveArcadeAuto100;
import frc.robot.commands.DefaultAuto;

// My change

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final DriveTrain m_drive = new DriveTrain();

  private final ColorSensor m_colorSensor = new ColorSensor();

  private final Shooter m_shooter = new Shooter();

  private final Intake m_intake = new Intake();
  
  private final XboxController m_controller1 = new XboxController(Constants.kController1);

  private final XboxController m_controller2 = new XboxController(Constants.kController2);

  private final DriveArcadeAuto50 m_driveArcadeAuto50;

  private final DriveArcadeAuto100 m_driveArcadeAuto100;

  private final DefaultAuto m_DefaultAuto;

  private final SendableChooser<Object> m_chooser;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_encoder.setDistancePerPulse(Math.PI*6/360);
    m_drive.setDefaultCommand(new DriveArcade(m_drive, m_controller1));
    m_colorSensor.setDefaultCommand(new ColorRead(m_colorSensor, m_controller1));
    m_shooter.setDefaultCommand(new Shoot(m_shooter, m_controller1));
    m_intake.setDefaultCommand(new IntakeCommand(m_intake, m_controller2));
    m_driveArcadeAuto50 = new DriveArcadeAuto50(m_drive, m_encoder);
    m_driveArcadeAuto100 = new DriveArcadeAuto100(m_drive, m_encoder);
    m_DefaultAuto = new DefaultAuto();
    m_chooser = new SendableChooser<Object>();

    //Adds auto commands
    m_chooser.setDefaultOption("Default Auto", m_DefaultAuto);
    m_chooser.addOption("DriveArcadeAuto50", m_driveArcadeAuto50);
    m_chooser.addOption("DriveArcadeAuto100", m_driveArcadeAuto100);
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() { 
    // An ExampleCommand will run in autonomous
    return (Command) m_chooser.getSelected();
  }
}
