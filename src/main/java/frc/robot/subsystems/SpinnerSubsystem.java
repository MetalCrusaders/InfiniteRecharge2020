/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SpinnerSubsystem extends SubsystemBase {
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor;
  private final ColorMatch m_colorMatcher;
  private final WPI_TalonSRX m_wheel;
  
  // Colors
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
 
  public SpinnerSubsystem() {
    m_wheel = new WPI_TalonSRX(Constants.WHEEL_MOTOR);
    m_colorSensor = new ColorSensorV3(i2cPort);
    m_colorMatcher = new ColorMatch();
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);

  }

  public String colorCheck() {
    Color detectedColor = m_colorSensor.getColor();
    String colorString = "";
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    
    if(true) {
      if (match.color == kBlueTarget) {
        colorString = "Blue";
      } else if (match.color == kRedTarget) {
        colorString = "Red";
      } else if (match.color == kGreenTarget) {
        colorString = "Green";
      } else if (match.color == kYellowTarget) {
        colorString = "Yellow";
      } else {
        colorString = "Unknown";
      }
    }

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);

    return colorString;
  }

  public void greenCheck() {
    String colorString = colorCheck();
    
    if((colorString.equals("Green") == false)){
      m_wheel.set(.2);
    }
    else if(colorString.equals("Green")){
      m_wheel.set(0);
    }
  }

  public void redCheck(){
    String colorString = colorCheck();
    if((colorString.equals("Red") == false)){
      m_wheel.set(.2);
    }
    else if(colorString.equals("Red")){
      m_wheel.set(0);
    }
  }

  public void blueCheck(){
    String colorString = colorCheck();
    if((colorString.equals("Blue") == false)){
      m_wheel.set(.2);
    }
    else if(colorString.equals("Blue")){
      m_wheel.set(0);
    }
  }

  public void yellowCheck(){
    String colorString = colorCheck();
    if( (colorString.equals("Yellow") == false)){
      m_wheel.set(.2);
    }
    else if(colorString.equals("Yellow")){
      m_wheel.set(0);
    }    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
