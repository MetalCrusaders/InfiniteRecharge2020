/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IndexerCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShootAuto;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.SpinnerCommand;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SpinnerSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.DriveArcade;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveTrain;




/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  

  private final DriveTrain m_drive = new DriveTrain();
  private final ClimbSubsystem m_climb = new ClimbSubsystem();
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final SpinnerSubsystem m_spinner = new SpinnerSubsystem();
  private final Indexer m_indexer = new Indexer();
  
  private final ShootAuto m_autoShoot = new ShootAuto(m_shooter, m_indexer, m_drive);

  private final XboxController m_controller1 = new XboxController(Constants.kController1);
  private final XboxController m_controller2 = new XboxController(Constants.kController2);

  private final SendableChooser<Object> m_chooser;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_drive.setDefaultCommand(new DriveArcade(m_drive, m_controller1));
    m_climb.setDefaultCommand(new ClimbCommand(m_climb,m_controller2));
    m_shooter.setDefaultCommand(new ShooterCommand(m_shooter,m_controller2));
    m_intake.setDefaultCommand(new IntakeCommand(m_intake,m_controller2));
    m_spinner.setDefaultCommand(new SpinnerCommand(m_spinner,m_controller2));
    m_indexer.setDefaultCommand(new IndexerCommand(m_indexer, m_controller2));
    m_chooser = new SendableChooser<Object>();

    m_chooser.setDefaultOption("shootAuto", m_autoShoot);
    SmartDashboard.putData("Auto Mode" , m_chooser);
    
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
