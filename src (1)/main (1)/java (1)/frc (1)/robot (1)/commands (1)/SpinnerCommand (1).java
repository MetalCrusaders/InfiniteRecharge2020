/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SpinnerSubsystem;

public class SpinnerCommand extends CommandBase {
  private final SpinnerSubsystem m_spinner;
  private final XboxController m_controller;

  public SpinnerCommand(SpinnerSubsystem spinner, XboxController controller) {
    m_spinner = spinner; 
    m_controller = controller; 
    addRequirements(m_spinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean isXPressed = m_controller.getXButton();
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    
    if(gameData.length() > 0 && isXPressed)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
          m_spinner.redCheck();
          break;
        case 'G' :
          m_spinner.yellowCheck();
          break;
        case 'R' :
          m_spinner.blueCheck();
          break;
        case 'Y' :
          m_spinner.greenCheck();
          break;
        default :
          //This is corrupt data
          break;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
