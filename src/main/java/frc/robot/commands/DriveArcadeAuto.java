/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveArcadeAuto extends CommandBase {
  private final DriveTrain m_drive;
  private final Encoder m_encoder;

  /**
   * Creates a new DriveArcadeAuto.
   */
  public DriveArcadeAuto(DriveTrain drive, Encoder encoder) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;
    m_encoder = encoder;

    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
    double move = 0.3;
    double rotate = 0;
    if(m_encoder.getDistance() < 50) {
    double dist = m_encoder.getDistance();
    SmartDashboard.putNumber("Encoder", dist);
      m_drive.arcadeDriveAuto(move, rotate);
      
    }
    */
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
