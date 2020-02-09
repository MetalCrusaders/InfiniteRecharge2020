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

public class DriveArcadeAuto50 extends CommandBase {
  private final DriveTrain m_drive;
  private final Encoder m_encoder;

  final double move = 0.5;
  final double rotate = 0;
  /**
   * Creates a new DriveArcadeAuto.
   */
  public DriveArcadeAuto50(DriveTrain drive, Encoder encoder) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;
    m_encoder = encoder;

    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_encoder.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    double dist = m_encoder.getDistance();
    SmartDashboard.putNumber("Encoder", dist);
    if (Math.abs(dist) < 50) {
      m_drive.arcadeDrive(move, rotate);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    m_drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}