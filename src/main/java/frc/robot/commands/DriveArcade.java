/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveArcade extends CommandBase {
  private final DriveTrain m_drive;
  private final XboxController m_controller;

  public DriveArcade(DriveTrain drive, XboxController controller) {
  
    m_drive = drive;
    m_controller = controller;

    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double move = m_controller.getY(Hand.kLeft);
    double rotate = m_controller.getX(Hand.kRight);
    boolean isAPressed = m_controller.getAButton();
    
    if(m_controller.getBButtonPressed()) {
      m_drive.resetGyro();
    }

    if(m_controller.getYButtonPressed()) {
      m_drive.calibrateGyro();
    }

    m_drive.autoDrive(move, rotate);

    m_drive.visionDrive(isAPressed);
 
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
