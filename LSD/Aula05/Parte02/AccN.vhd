library IEEE;
use IEEE.STD_LOGIC_1164.all;


entity AccN is

	generic (N       : positive := 8);
	port 	  (reset   : in std_logic; 
				enable  : in std_logic;
				clock   : in std_logic;
				dataIn  : in  std_logic_vector((N-1) downto 0);
				dataOut : out std_logic_vector((N-1) downto 0));
				
end AccN;

architecture Structural of AccN is
	signal s_adderOut, s_regOut : std_logic_vector((N-1) downto 0);
begin

adder: entity work.AdderN(Behavioral)

generic map(n => n)
port map(operand0 => dataIn,
			operand1 => s_regOut,
			result => s_adderOut);
			
reg: entity work.RegN(Behavioral)
	
generic map(n => n)
port map(reset => reset,
			enable => enable,
			clock => clock,
			dataIn => s_adderOut,
			dataOut => s_regOut);
			
dataOut <= s_regOut;
			
end Structural;